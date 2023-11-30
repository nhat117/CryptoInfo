package com.example.apitest.data.remote.dto

import com.example.apitest.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

data class CoinDetailDTO(
    val description: String,
    @SerializedName("development_status") val developmentStatus: String,
    @SerializedName("first_data_at") val firstDataAt: String,
    @SerializedName("hardware_wallet") val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm") val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("last_data_at") val lastDataAt: String,
    val links: Links,
    @SerializedName("links_extended") val linksExtended: List<LinksExtended>,
    val logo: String,
    val message: String,
    val name: String,
    @SerializedName("open_source") val openSource: Boolean,
    @SerializedName("org_structure") val orgStructure: String,
    @SerializedName("proof_type") val proofType: String,
    val rank: Int,
    @SerializedName("started_at") val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String,
    @SerializedName("whitepaper") val whitePaper: Whitepaper
)

fun CoinDetailDTO.toCoinDetail(): CoinDetail {
    return CoinDetail(id, name, description, symbol, rank, isActive, tags.map { it.name }, team)
}