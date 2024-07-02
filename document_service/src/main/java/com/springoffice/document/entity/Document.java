package com.springoffice.document.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Document {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String path;
    private String title;
    private String message;
    @JsonProperty("uploader_id")
    private Integer uploaderId;
    @JsonProperty("upload_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp uploadTime;

    @TableField(exist = false)
    @JsonProperty("uploader_name")
    private String uploaderName;
}
