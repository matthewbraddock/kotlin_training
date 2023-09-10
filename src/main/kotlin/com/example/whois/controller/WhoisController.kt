package com.example.whois.controller

import com.example.whois.service.WhoIsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("api")
class WhoisController(private val whoIsService: WhoIsService) {

    @GetMapping("/getWhoIs")
    fun getWhoIs(@RequestParam domainName: String): ResponseEntity<String> {
        val whoIsInfo = whoIsService.getWhoIs(domainName)
        return ResponseEntity.ok(whoIsInfo)
    }
}
