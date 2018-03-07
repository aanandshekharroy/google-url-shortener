package com.example.theseus.urlshortener.data.api.model.response

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class UrlShortenResponse(
    @field:SerializedName("kind")
    val kind: String,
    @field:SerializedName("longUrl")
    val longUrl: String,
    @field:SerializedName("id")
    val id: String
)