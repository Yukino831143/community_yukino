package tech.yukino.community.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;//文章
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//首页
    private boolean showNext;//下一页
    private boolean showEndPage;//尾页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//当前分页栏显示的页码集合
    private Integer totalPage;//

    /**
     *
     * @param totalCount 问题总数
     * @param page 地址栏的当前页数
     * @param size 每一页的问题数量
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {

            totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);

            if (page < 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            this.page = page;

            //计算当前页的分页栏显示的页码集合
            pages.add(page);
            for (int i = 1;i <= 3; i++) {
                if (page - i > 0) {
                    pages.add(0, page - i);
                }
                if (page + i <= totalPage) {
                    pages.add(page + i);
                }
            }
            //是否展示上一页标签
            if (page == 1) {
                showFirstPage = false;
            } else {
                showFirstPage = true;
            }
            //是否展示下一页标签
            if (page == totalPage) {
                showEndPage = false;
            } else {
                showEndPage = true;
            }

            //是否展示首页箭头，只要没包含第一页，就显示首页标签
            if (pages.contains(1)) {
                showFirstPage = false;
            } else {
                showFirstPage = true;
            }
            //是否展示尾页箭头
            if (pages.contains(totalPage)) {
                showEndPage = false;
            } else {
                showEndPage = true;
            }

    }
}
