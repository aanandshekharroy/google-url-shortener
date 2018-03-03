package com.example.theseus.urlshortener.data.model.response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class UrlShortenResponse(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("longUrl")
	val longUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)