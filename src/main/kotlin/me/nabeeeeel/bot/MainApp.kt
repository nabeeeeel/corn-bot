package me.nabeeeeel.bot

import com.gitlab.kordlib.common.entity.Snowflake
import com.gitlab.kordlib.kordx.emoji.Emojis
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.profileLink
import java.awt.Color


suspend fun main(args: Array<String>) {

    val token = args.first()
    bot(token) {
        prefix {
            val configuration = discord.getInjectionObjects(me.nabeeeeel.bot.data.Configuration::class)
            guild?.let { configuration[it.id.longValue]?.prefix } ?: "<none>"
        }
        configure {
            theme = Color(255, 205, 22)
            allowMentionPrefix = true
            commandReaction = Emojis.corn
        }

        mentionEmbed {
            val discord = it.discord
            val api = discord.api
            val versions = discord.versions

            author {
                val author = api.getUser(Snowflake(125991444212088832))
                icon = author?.avatar?.url
                name = author?.tag
                url = author?.profileLink
            }

            title = "CornBot"
            description = "CornBot is here to emulate the lovable Corn. Corn likes and says lots of stuff. Corn loves: CornBot."
            thumbnail {
                url = api.getSelf().avatar.url
            }

            footer {
                text = "Version 1.0.0 - ${versions.library} - ${versions.kord}"
            }

            field {
                name = "Prefix"
                value = it.prefix()
            }

            field {
                name = "Links"
                value = "[[See Code]](https://github.com/nabeeeeel/corn-bot)"
                inline = true
            }

            field {
                value = "[[My Twitter]](https://www.twitter.com/basicBleu)"
                inline = true
            }

            field {
                value = "[[My Twitch]](https://www.twitch.tv/basicBleu)"
                inline = true
            }
        }

        presence {
            playing("Corn likes: CornBot")
        }
    }
}