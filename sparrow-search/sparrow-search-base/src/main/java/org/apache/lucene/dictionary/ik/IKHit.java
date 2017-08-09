package org.apache.lucene.dictionary.ik;

/**
 * Created by yangtao on 2016/1/23.
 */
public class IKHit {
    //Hit不匹配
    private static final int UNMATCH = 0x00000000;
    //Hit完全匹配
    private static final int MATCH = 0x00000001;
    //Hit前缀匹配
    private static final int PREFIX = 0x00000010;


    //该HIT当前状态，默认未匹配
    private int hitState = UNMATCH;

    //记录词典匹配过程中，当前匹配到的词典分支节点
    private IKDictSegment matchedDictSegment;
    /*
     * 词段开始位置
     */
    private int begin;
    /*
     * 词段的结束位置
     */
    private int end;


    /**
     * 判断是否完全匹配
     */
    public boolean isMatch() {
        return (this.hitState & MATCH) > 0;
    }
    /**
     *
     */
    public void setMatch() {
        this.hitState = this.hitState | MATCH;
    }

    /**
     * 判断是否是词的前缀
     */
    public boolean isPrefix() {
        return (this.hitState & PREFIX) > 0;
    }
    /**
     *
     */
    public void setPrefix() {
        this.hitState = this.hitState | PREFIX;
    }
    /**
     * 判断是否是不匹配
     */
    public boolean isUnmatch() {
        return this.hitState == UNMATCH ;
    }
    /**
     *
     */
    public void setUnmatch() {
        this.hitState = UNMATCH;
    }

    public IKDictSegment getMatchedDictSegment() {
        return matchedDictSegment;
    }

    public void setMatchedDictSegment(IKDictSegment matchedDictSegment) {
        this.matchedDictSegment = matchedDictSegment;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
