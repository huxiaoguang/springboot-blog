package main.blog.vo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 * @author huxg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable
{
    /**操作人*/
    private String createBy;

    /**更新人*/
    private String updateBy;

    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
