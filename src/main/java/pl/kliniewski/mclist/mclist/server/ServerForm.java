package pl.kliniewski.mclist.mclist.server;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ServerForm
{
    @NotNull
    @Size(min = 6, max = 32)
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
