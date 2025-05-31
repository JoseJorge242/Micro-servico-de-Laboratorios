package client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dto.SoftwareDTO;

@FeignClient(name = "software-service", url = "http://localhost:8082")
public interface SoftwareClient {

    @GetMapping("/softwares/{id}")
    SoftwareDTO getSoftwareById(@PathVariable Long id);

    @GetMapping("/softwares")
    List<SoftwareDTO> getAll();
}