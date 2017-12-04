package com.arawn.cms.controller.admin;

import com.arawn.cms.entity.EasyUIResult;
import com.arawn.cms.entity.PageBean;
import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import com.arawn.cms.util.FastJsonUtil;
import com.arawn.cms.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tag标签后台Controller
 * Created by ArawN on 2017/12/4.
 */
@Controller
@RequestMapping("/admin/tag")
public class TagAdminController {

    @Resource
    private TagService tagService;

    /**
     * 分页查询Tag标签信息
     * @param page
     * @param rows
     * @param sName
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer rows,
                       @RequestParam(value = "sName", required = false) String sName) throws Exception {
        PageBean pageBean = new PageBean(page, rows);

        // 封装查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("name", StringUtil.formatLike(sName));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Tag> tagList = tagService.listByMap(map);
        Long tagCount = tagService.countByMap(map);

        EasyUIResult<Tag> result = new EasyUIResult<>(tagList, tagCount);
        return FastJsonUtil.toJSONString(result);
    }

    /**
     * 删除Tag标签信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String ids) throws Exception {
        String[] idArr = ids.split(",");

        // 遍历删除
        for (String id : idArr) {
            tagService.deleteById(Integer.parseInt(id));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return FastJsonUtil.collectToString(map);
    }

    /**
     * 保存Tag标签信息
     * @param tag
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Tag tag) throws Exception {
        // 操作记录条数
        int resultTotal = 0;

        if (tag.getId() == null) {
            resultTotal = tagService.insert(tag);
        } else {
            resultTotal = tagService.updateById(tag);
        }

        Map<String, Object> map = new HashMap<>();
        if (resultTotal > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return FastJsonUtil.collectToString(map);
    }

}
