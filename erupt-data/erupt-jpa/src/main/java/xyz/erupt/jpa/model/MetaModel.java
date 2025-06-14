package xyz.erupt.jpa.model;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.config.EruptSmartSkipSerialize;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.core.context.MetaContext;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author YuePeng
 * date 2018-10-11.
 */
@Getter
@Setter
@MappedSuperclass
public class MetaModel extends BaseModel {

    @EruptField(views = @View(title = "创建人", show = false))
    @EruptSmartSkipSerialize
    private String createBy;

    @EruptField(views = @View(title = "创建时间", show = false))
    @EruptSmartSkipSerialize
    private LocalDateTime createTime;

    @EruptField(views = @View(title = "更新人", show = false))
    @EruptSmartSkipSerialize
    private String updateBy;

    @EruptField(views = @View(title = "更新时间", show = false))
    @EruptSmartSkipSerialize
    private LocalDateTime updateTime;

    @PrePersist
    protected void persist() {
        this.setCreateTime(LocalDateTime.now());
        Optional.ofNullable(MetaContext.getUser()).ifPresent(it -> this.setCreateBy(MetaContext.getUser().getName()));
        this.update();
    }

    @PreUpdate
    protected void update() {
        this.setUpdateTime(LocalDateTime.now());
        Optional.ofNullable(MetaContext.getUser()).ifPresent(it -> this.setUpdateBy(MetaContext.getUser().getName()));
    }


}
