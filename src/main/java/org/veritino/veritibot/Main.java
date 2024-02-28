package org.veritino.veritibot;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        Path file = Paths.get("token.txt");
        final String TOKEN = Files.readString(file);
        System.out.println(TOKEN);
        DiscordClient.create(TOKEN)
                .withGateway(client ->
                        client.on(MessageCreateEvent.class, event -> {
                            Message message = event.getMessage();
                            switch (message.getContent()) {
                                case "$hello":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("Hello!"));
                                case "$github":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("Github: https://github.com/veritino"));
                                case "$discord":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("Discordサーバー: https://discord.gg/UY9Nx4Dnvg"));
                                case "$website":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("ウェブサイト: https://veritino.pages.dev"));
                                case "$x":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("X: https://twitter.com/Veritino_x"));
                                case "$fuckyou":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("F**k you too!"));
                                case "$死ね":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("お前が死ねよ"));
                                case "$ping":
                                    return message.getChannel()
                                            .flatMap(channel -> channel.createMessage("Pong!"));
                                case "$じゃんけん":
                                    int jankenRandom = rand.nextInt(2);
                                    if (jankenRandom == 0) {
                                        return message.getChannel()
                                                .flatMap(channel -> channel.createMessage(":fist:!"));
                                    } else if (jankenRandom == 1) {
                                        return message.getChannel()
                                                .flatMap(channel -> channel.createMessage(":v:!"));
                                    } else if (jankenRandom == 2) {
                                        return message.getChannel()
                                                .flatMap(channel -> channel.createMessage(":raised_hand:!"));
                                    }
                            }

//                            if (message.getContent().equalsIgnoreCase("$hello")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("Hello!"));
//                            } else if (message.getContent().equalsIgnoreCase("$github")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("Github: https://github.com/veritino"));
//                            } else if (message.getContent().equalsIgnoreCase("$discord")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("Discordサーバー: https://discord.gg/UY9Nx4Dnvg"));
//                            } else if (message.getContent().equalsIgnoreCase("$website")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("ウェブサイト: https://veritino.pages.dev"));
//                            } else if (message.getContent().equalsIgnoreCase("$x")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("X: https://twitter.com/Veritino_x"));
//                            } else if (message.getContent().equalsIgnoreCase("fuckyou")) {
//                                return message.getChannel()
//                                        .flatMap(channel -> channel.createMessage("F**k you too!"));
//                            }

                            return Mono.empty();
                        }))
                .block();
    }
}