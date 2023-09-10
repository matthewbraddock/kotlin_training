package com.example.whois.controller

import com.example.whois.service.WhoIsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class WhoisController(private val whoIsService: WhoIsService) {

    @GetMapping("/getWhoIs")
    fun getWhoIs(@RequestParam(required = false) domainName: String?): String {
        if (domainName != null) {
            val whoIsInfo = whoIsService.getWhoIs(domainName)
            return whoIsInfo
        }
        return "No domainName provided."
    }
}


