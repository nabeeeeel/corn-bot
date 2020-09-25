package me.nabeeeeel.bot

import com.gitlab.kordlib.common.entity.Snowflake
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.profileLink
import me.nabeeeeel.bot.extensions.requiredPermissionLevel
import me.nabeeeeel.bot.services.PermissionsService
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
                text = "Version 0.0.1a - ${versions.library} - ${versions.kord}"
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

        // initialize all uninitialized guilds
//            api.guilds.toList().filter { configuration[it.id.longValue] == null }.forEach {
//
//                configuration[it.id.longValue] = GuildConfiguration("-")
//                configuration.save()
//            }

        presence {
            listening("SUGGESTIONS")
        }

        permissions {
            val guild = guild ?: return@permissions false
            val member = user.asMember(guild.id)
            val permission = command.requiredPermissionLevel

            val permissionsService = discord.getInjectionObjects(PermissionsService::class)
            permissionsService.hasClearance(member, permission)
        }
    }
}