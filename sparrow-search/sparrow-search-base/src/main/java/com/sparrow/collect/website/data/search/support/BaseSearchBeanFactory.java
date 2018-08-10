package com.sparrow.collect.website.data.search.support;

import com.sparrow.collect.strategy.*;
import com.sparrow.collect.website.SearchIdDef;
import com.sparrow.collect.website.data.search.SearchBean;
import com.sparrow.collect.website.data.search.SearchBeanFactory;
import com.sparrow.collect.website.data.search.UserFactor;
import com.sparrow.collect.website.domain.search.BaseSearchFactor;
import com.sparrow.collect.website.format.KeywordFormatManager;
import com.sparrow.collect.website.query.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.queries.ChainedFilter;
import org.apache.lucene.search.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangtao on 2015/4/17.
 */
public class BaseSearchBeanFactory extends SearchBeanFactory {

    @Override
    public SearchBean createSearchBean(String searchId, Object params) {
        BaseSearchBean searchBean = new BaseSearchBean();
        searchBean.setSearchId(searchId);
        searchBean.setParamsBean(params);
        try {
            BaseSearchFactor factor = (BaseSearchFactor) params;
            setUserInfo(factor, searchBean);
            formatKeyword(factor, searchBean);

            Query query = buildQuery(searchId, searchBean.getSearchCondStr());
            searchBean.setQuery(query);

            Filter filter = buildFilter(searchId, factor);
            searchBean.setFilter(filter);

            Sort sort = buildSort(searchId, factor);
            searchBean.setSort(sort);

            Pagination pagination = buildPagination(factor);
            searchBean.setPagination(pagination);
        } catch (Exception e) {
            log.error("build searchBean error : ", e);
        }
        return searchBean;
    }

    /**
     * 设置用户信息
     *
     * @param factor
     * @param searchBean
     */
    protected void setUserInfo(BaseSearchFactor factor, SearchBean searchBean) {
        UserFactor userFactor = factor.getUserFactor();
        if (userFactor != null) {
            searchBean.setUserId(userFactor.getUserId() == null ? null : userFactor.getUserId().toString());
            searchBean.setUserSign(userFactor.getUserSign() == null ? null : userFactor.getUserSign());
        }
    }

    /**
     * 格式化搜索关键词
     *
     * @param factor
     * @param searchBean
     * @throws Exception
     */
    protected void formatKeyword(BaseSearchFactor factor, SearchBean searchBean) throws Exception {
        String keyword = factor.getKeyword();
        KeywordFormatManager.getInstance().keywordFormat(searchBean, searchBean.getSearchId(), keyword);
    }

    /**
     * 构建lucene搜索query
     *
     * @param searchId
     * @param formattedKeyword
     * @return
     * @throws Exception
     */
    protected Query buildQuery(String searchId, String formattedKeyword) throws Exception {
        if (searchId.equals(SearchIdDef.PC_STORE_SEARCHER) || searchId.equals(SearchIdDef.PC_GOODS_SEARCHER)) {
            List<FieldStrategy> fieldStrategies = StrategyManager.getInstance().getFieldStrategy(searchId);
            Query query = StrategyFactory.create(formattedKeyword, fieldStrategies);
            return query;
        }
        BooleanQuery query = new BooleanQuery();
        List<StrategyBean> strategyBeans = StrategyArgInfoBuilder.getInstance().build(null, null, formattedKeyword, searchId);
        StrategyManager.getInstance().strategy(null, searchId, strategyBeans, query);
        if (query.clauses().size() == 0) {
            MatchAllDocsQuery queryAll = new MatchAllDocsQuery();
            query.add(queryAll, BooleanClause.Occur.MUST);
        }
        return query;
    }

    /**
     * 构建lucene搜索filter
     *
     * @param searchId
     * @param factor
     * @return
     */
    protected Filter buildFilter(String searchId, BaseSearchFactor factor) {
        List<SearchFilter> searchFilters = factor.getFilters();
        if (CollectionUtils.isEmpty(searchFilters)) {
            return null;
        }
        List<Filter> filterList = new ArrayList<>();
        for (SearchFilter searchFilter : searchFilters) {
            Filter filter = FilterBuilder.getInstance().build(searchId, searchFilter);
            if (filter != null) {
                filterList.add(filter);
            }
        }
        if (filterList.isEmpty()) {
            return null;
        }
        Filter[] filters = new Filter[filterList.size()];
        filterList.toArray(filters);
        ChainedFilter chainFilter = new ChainedFilter(filters, ChainedFilter.AND);
        return chainFilter;
    }

    /**
     * 构建lucene搜索sort
     *
     * @param searchId
     * @param factor
     * @return
     */
    protected Sort buildSort(String searchId, BaseSearchFactor factor) {
        List<SearchSorter> searchSorters = factor.getSorters();
        if (CollectionUtils.isEmpty(searchSorters)) {
            return null;
        }
        List<SortField> sortFields = new ArrayList<>(searchSorters.size());
        for (SearchSorter sorter : searchSorters) {
            SortField sortField = SortBuilder.getInstance().build(sorter);
            if (sortField != null) {
                sortFields.add(sortField);
            }
        }
        SortField[] fields = new SortField[sortFields.size()];
        sortFields.toArray(fields);
        Sort sort = new Sort(fields);
        return sort;
    }

    /**
     * 构造lucene搜索分页对象
     *
     * @param factor
     * @return
     */
    protected Pagination buildPagination(BaseSearchFactor factor) {
        Pagination pagination = new Pagination();
        com.dili.dd.searcher.datainterface.domain.Pagination page = factor.getPagination();
        if (page == null) {
            pagination.setPageNo(1);
            pagination.setPageSize(50);
        } else {
            pagination.setPageNo(page.getPageNumber() < 1 ? 1 : page.getPageNumber());
            pagination.setPageSize(page.getPageSize() > PAGE_SIZE ? PAGE_SIZE : page.getPageSize());
        }
        return pagination;
    }
}
