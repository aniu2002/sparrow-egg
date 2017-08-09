package com.sparrow.app.information.service.met;

import java.util.List;



import com.sparrow.orm.page.PageResult;

import com.sparrow.app.information.domain.met.LfMembers;

/**
 * 
 * 完成数据库表(lf_members-)的增删改查功能<br/>  
 *
 * lf_members:  
 *
 * @author YZC  
 * @version 2.0
 * date: 2017-08-03 01:32:20
 */
public interface LfMembersService {

	/**
	 * 
	 * 增加保存()信息 <br/>    
	 *  
	 * @author YZC  
	 * @param lfMembers 
	 *        
	 * @return
	 * @throws Exception  
	 *         可能抛出数据库操作异常 
	 */
    public void saveLfMembers(LfMembers lfMembers) throws Exception ;
    
    /**
	 * 
	 * 查询()列表信息 <br/>      
	 *  
	 * @author YZC  
	 * @param lfMembers 
	 *        查询条件
	 * @return 返回列表
	 * @throws Exception 
	 *         可能抛出数据库操作异常  
	 */
    public PageResult listLfMembers(LfMembers lfMembers) throws Exception ;
  
  	/**
	 * 
	 * 分页查询()列表信息 <br/>   
	 *  
	 * @author YZC  
	 * @param lfMembers 
	 *        查询条件
	 * @return 返回分页包装信息
	 * @throws Exception 
	 *         可能抛出数据库操作异常 
	 */
    public PageResult pageListLfMembers(LfMembers lfMembers,int page, int limit) throws Exception ;
  
  	/**
	 * 
	 * 更新()信息 <br/>      
	 *  
	 * @author YZC  
	 * @param lfMembers
	 *        
	 * @return
	 * @throws Exception 
	 *         可能抛出数据库操作异常  
	 */
    public void updateLfMembers(LfMembers lfMembers) throws Exception ;
  
  	/**
	 * 
	 * 根据lfMembersId获取()实例信息<br/>      
	 *  
	 * @author YZC  
	 * @param lfMembersId 
	 *        的ID值
	 * @return 返回对应ID的()实体 
	 * @throws Exception 
	 *         可能抛出数据库操作异常 
	 */
    public LfMembers getLfMembersById(Long lfMembersId) throws Exception ;
    
    /**
	 * 
	 * 根据lfMembersId删除()信息<br/>      
	 *  
	 * @author YZC  
	 * @param lfMembersId 
	 *        的ID值
	 * @return
	 * @throws Exception 
	 *         可能抛出数据库操作异常 
	 */
    public void deleteLfMembersById(Long lfMembersId) throws Exception ;
    
    public void batchDeleteLfMembers(List<Long> ids) throws Exception;
}