package pl.kliniewski.mclist.mclist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kliniewski.mclist.mclist.server.Server;
import pl.kliniewski.mclist.mclist.server.ServerForm;
import pl.kliniewski.mclist.mclist.server.ServerRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/server")
public class ServerController
{
    private final ServerRepository serverRepository;

    @Autowired
    public ServerController(ServerRepository serverRepository)
    {
        this.serverRepository = serverRepository;
    }

    @GetMapping("/list")
    public String controllerList(Model model)
    {
        model.addAttribute("servers", this.serverRepository.findAll());
        model.addAttribute("serverAddForm", new ServerForm());

        return "servers";
    }

    @GetMapping("/delete/{server}")
    public String deleteServer(@PathVariable Optional<Server> server)
    {
        server.ifPresent(this.serverRepository::delete);
        return "redirect:/server/list";
    }

    @PostMapping("/add")
    public String addServer(@Valid ServerForm serverAddForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "redirect:/server/list";
        }
        Server server = new Server();
        server.setName(serverAddForm.getName());
        this.serverRepository.save(server);

        return "redirect:/server/list";
    }

    @GetMapping("/edit/{server}")
    public String editServer(@PathVariable Optional<Server> server, Model model)
    {
        if (server.isPresent())
        {
            server.ifPresent(s ->
            {
                ServerForm serverForm = new ServerForm();
                serverForm.setName(s.getName());
                model.addAttribute("server", s);
                model.addAttribute("serverEditForm", serverForm);
            });
            return "servers";
        }
        else
        {
            return "redirect:/server/list";
        }
    }

    @PostMapping("/edit/{server}")
    public String editServer(@PathVariable Optional<Server> server, @Valid ServerForm serverEditForm,
            BindingResult bindingResult, Model model)
    {
        if (server.isEmpty() || bindingResult.hasErrors())
        {
            return "redirect:/server/list";
        }
        Server s = server.get();
        s.setName(serverEditForm.getName());
        this.serverRepository.save(s);
        return "redirect:/server/list";
    }
}
