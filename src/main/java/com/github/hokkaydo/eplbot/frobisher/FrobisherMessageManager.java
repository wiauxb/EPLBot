package com.github.hokkaydo.eplbot.frobisher;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;


// Frobisher is a shapeshifter creature,
// this is a geek reference to say that this class is
// capable of sending messages using the appearance of another user
public class FrobisherMessageManager {

    private WebhookClient client;
    private Guild guild;

    public FrobisherMessageManager(Guild guild) {
        this.guild = guild;
        this.client = WebhookClient.withUrl("https://discordapp.com/api/webhooks/1120494561198874704/O6SB8deiLBiwKOJeTThPUFPJA18vz45bCuvqnJUjfb55S-bV9lsfEw6UDRsHgFSfPuFi");
    }

    public void sendMessage(Message message, User author) {
        // Change appearance of webhook message
        WebhookMessageBuilder builder = new WebhookMessageBuilder();

        Member authorMember = guild.getMemberById(author.getIdLong());
        boolean hasNickname = authorMember != null && authorMember.getNickname() != null;
        String authorNickAndName = (hasNickname  ? authorMember.getNickname() + " (" : "") + author.getName() + (hasNickname ? ")" : "");
        
        builder.setUsername(authorNickAndName); // use this username
        builder.setAvatarUrl(author.getAvatarUrl()); // use this avatar
        builder.setContent(message.getContentRaw());
        client.send(builder.build());
    }

    @Override
    protected void finalize() throws Throwable {
        client.close();
    }

}