package pl.kliniewski.mclist.mclist.server;

import pl.kliniewski.mclist.mclist.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Server
        extends AbstractBaseEntity
{
    @Column(length = 32, nullable = false)
    private String name;

    public Server()
    {
        super(null);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
