package com.xinf.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinf.entity.ApplyAnchorRecord;
import com.xinf.service.ApplyAnchorRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (ApplyAnchorRecord)表控制层
 *
 * @author makejava
 * @since 2021-09-11 11:17:09
 */
@Slf4j
@Api(value = "ApplyAnchorRecordController", tags = { "应用记录访问接口" })
@RestController
@RequestMapping("applyAnchorRecord")
public class ApplyAnchorRecordController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ApplyAnchorRecordService applyAnchorRecordService;

    /**
     * 分页查询所有数据
     *
     * @param applyAnchorRecord 查询实体
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "选择所有信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "applyAnchorRecord", value = "应用记录"),
             @ApiImplicitParam(name ="pageCurrent", value = "当前界面"),
             @ApiImplicitParam(name ="pageSize", value = "界面尺寸"),
    })

    public R selectAll(ApplyAnchorRecord applyAnchorRecord,
            @RequestParam(defaultValue = "10") long pageSize, @RequestParam(defaultValue = "1") long pageCurrent) {
        Page page = new Page(pageCurrent, pageSize, true);
        return success(this.applyAnchorRecordService.page(page, new QueryWrapper<>(applyAnchorRecord)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "选择单个信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "序列名称")})

    public R selectOne(@PathVariable Serializable id) {
        return success(this.applyAnchorRecordService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param applyAnchorRecord 实体对象
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "增加信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "applyAnchorRecord", value = "应用记录")
    })
    public R insert(@RequestBody ApplyAnchorRecord applyAnchorRecord) {
        return success(this.applyAnchorRecordService.save(applyAnchorRecord));
    }

    /**
     * 修改数据
     *
     * @param applyAnchorRecord 实体对象
     * @return 修改结果
     */
    @PutMapping
    @ApiOperation(value = "修改数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "applyAnchorRecord", value = "应用记录")
    })
    public R update(@RequestBody ApplyAnchorRecord applyAnchorRecord) {
        return success(this.applyAnchorRecordService.updateById(applyAnchorRecord));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation(value = "删除数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "ID列表")
    })
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.applyAnchorRecordService.removeByIds(idList));
    }
}
