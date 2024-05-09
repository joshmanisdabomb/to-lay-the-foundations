package net.jidb.to.base.wiki.reference

import net.minecraft.resources.ResourceLocation

data class WikiBasicReference(override val registry: ResourceLocation, override val identifier: ResourceLocation) : WikiReference()
