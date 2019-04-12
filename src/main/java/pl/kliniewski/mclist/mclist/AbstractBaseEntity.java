package pl.kliniewski.mclist.mclist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractBaseEntity
        implements Persistable<UUID>
{
    @Id
    @Column(length = 16, nullable = false, unique = true)
    private UUID id;

    @Transient
    private boolean persisted;

    public AbstractBaseEntity(UUID id)
    {
        this.id = id != null ? id : UUID.randomUUID();
    }

    @Override
    public UUID getId()
    {
        return this.id;
    }

    @Override
    @JsonIgnore
    public boolean isNew()
    {
        return !this.persisted;
    }

    @PostPersist
    @PostLoad
    private void setPersisted()
    {
        this.persisted = true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof AbstractBaseEntity))
        {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(id, that.id);
    }
}
