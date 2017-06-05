package com.chn.common.persistence;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 *
 * @param <T>
 */
@Data
public class Page<T> {
    private int pageNum;
    private int pageSize;
    private long total;

    private long count;// 总记录数，设置为“-1”表示不查询总数

    private int first;// 首页索引
    private int last;// 尾页索引
    private int prev;// 上一页索引
    private int next;// 下一页索引

    private boolean firstPage;//是否是第一页
    private boolean lastPage;//是否是最后一页

    private int length = 8;// 显示页面长度
    private int slider = 1;// 前后显示页面长度

    private List<T> result = new ArrayList<T>();

    private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

    private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。

    private String funcParam = ""; // 函数的附加参数，第三个参数值。

    private String message = ""; // 设置提示消息，显示在“共n条”之后


    /**
     * 初始化参数
     */
    public void initialize() {

        //1
        this.first = 1;

        this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (pageNum <= 1) {
            this.pageNum = this.first;
            this.firstPage = true;
        }

        if (this.pageNum >= this.last) {
            this.pageNum = this.last;
            this.lastPage = true;
        }

        if (this.pageNum < this.last - 1) {
            this.next = this.pageNum + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNum > 1) {
            this.prev = this.pageNum - 1;
        } else {
            this.prev = this.first;
        }

        //2
        if (this.pageNum < this.first) {// 如果当前页小于首页
            this.pageNum = this.first;
        }

        if (this.pageNum > this.last) {// 如果当前页大于尾页
            this.pageNum = this.last;
        }

    }

    /**
     * 默认输出当前分页标签
     * <div class="page">${page}</div>
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (pageNum == first) {// 如果是首页
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
        } else {
            sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + prev + "," + pageSize + ",'" + funcParam + "');\">&#171; 上一页</a></li>\n");
        }

        int begin = pageNum - (length / 2);

        if (begin < first) {
            begin = first;
        }

        int end = begin + length - 1;

        if (end >= last) {
            end = last;
            begin = end - length + 1;
            if (begin < first) {
                begin = first;
            }
        }

        if (begin > first) {
            int i = 0;
            for (i = first; i < first + slider && i < begin; i++) {
                sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + "," + pageSize + ",'" + funcParam + "');\">"
                        + (i + 1 - first) + "</a></li>\n");
            }
            if (i < begin) {
                sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
            }
        }

        for (int i = begin; i <= end; i++) {
            if (i == pageNum) {
                sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - first)
                        + "</a></li>\n");
            } else {
                sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + "," + pageSize + ",'" + funcParam + "');\">"
                        + (i + 1 - first) + "</a></li>\n");
            }
        }

        if (last - end > slider) {
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
            end = last - slider;
        }

        for (int i = end + 1; i <= last; i++) {
            sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + "," + pageSize + ",'" + funcParam + "');\">"
                    + (i + 1 - first) + "</a></li>\n");
        }

        if (pageNum == last) {
            sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
        } else {
            sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + next + "," + pageSize + ",'" + funcParam + "');\">"
                    + "下一页 &#187;</a></li>\n");
        }

        sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
        sb.append("<input type=\"text\" value=\"" + pageNum + "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
        sb.append(funcName + "(this.value," + pageSize + ",'" + funcParam + "');\" onclick=\"this.select();\"/> / ");
        sb.append("<input type=\"text\" value=\"" + pageSize + "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
        sb.append(funcName + "(" + pageNum + ",this.value,'" + funcParam + "');\" onclick=\"this.select();\"/> 条，");
        sb.append("共 " + count + " 条" + (message != null ? message : "") + "</a></li>\n");

        sb.insert(0, "<ul>\n").append("</ul>\n");

        sb.append("<div style=\"clear:both;\"></div>");

        return sb.toString();
    }

    /**
     * 获取分页HTML代码
     *
     * @return
     */
    public String getHtml() {
        return toString();
    }
}
