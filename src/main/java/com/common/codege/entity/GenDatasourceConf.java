
package com.common.codege.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据源表
 *
 * @author wangchao
 * @date 2020-11-19 11:17:34
 */
@Data
@TableName("gen_datasource_conf")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "数据源表")
public class GenDatasourceConf extends Model<GenDatasourceConf> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
//    @TableId  这里存在bug自动生成器没考虑这个问题,数据库设置主键自增,在这里啥都不添加,默认ASSIGN_ID(雪花算法)
    @ApiModelProperty(value="主键")
    private Integer id;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String name;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String url;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String username;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String password;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createDate;
    /**
     * 更新
     */
    @ApiModelProperty(value="更新")
    private LocalDateTime updateDate;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String delFlag;
    }
