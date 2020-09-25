package me.nabeeeeel.bot.commands

import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.dsl.commands
import me.nabeeeeel.bot.services.ListService


fun CornCommands(listService: ListService) = commands("Corn") {

    command("Says") {
        description = "Corn says things."
        execute(EveryArg("phrase")) {
            val phrase = args.first
            var newString = ""
            phrase.forEach {
                newString += it.toUpperCase() + " "
            }
            respond {
                title = "C O R N  S A Y S : $newString"
                color = discord.configuration.theme
                image = listService.cornImages.random()
            }
        }
    }

    command("Likes") {
        description = "Corn likes things."
        execute(EveryArg("phrase")) {
            val phrase = args.first
            var newString = ""
            phrase.forEach {
                newString += it.toUpperCase() + " "
            }
            respond {
                title = "C O R N  L I K E S : $newString"
                color = discord.configuration.theme
                image = listService.cornImages.random()
            }
        }
    }

    command("Yawns") {
        description = "Corn yawns sometimes"
        execute() {
            respond {
                title = "Y A W N"
                color = discord.configuration.theme
                image = "https://cdn.discordapp.com/attachments/753796354072903731/758945550560133120/corn_yawn.gif"
            }
        }
    }
}