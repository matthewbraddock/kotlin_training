package com.example.whois.service

import org.springframework.stereotype.Component
import com.example.whois.repository.WhoIsRepository

@Component
class WhoIsService(private val whoIsRepository: WhoIsRepository) {

    fun getWhoIs(domainName: String): String {
        return whoIsRepository.getWhoIs(domainName)
    }
}
