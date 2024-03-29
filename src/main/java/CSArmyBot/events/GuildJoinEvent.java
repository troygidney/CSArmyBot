package CSArmyBot.events;

import java.awt.Color;
import java.time.OffsetDateTime;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class GuildJoinEvent implements EventListener {

	@Override
	public void onEvent(Event e) {
		if (e instanceof GuildMemberJoinEvent){
			GuildMemberJoinEvent event = (GuildMemberJoinEvent) e;


			TextChannel channel = event.getGuild().getTextChannelsByName("leave-join-log", true).get(0);
			
			channel.sendMessage(onJoin((event).getUser(), (event).getGuild()).build()).queue();
			//channel.sendMessage(event.getMember().getUser().getId()).queue();
		}
		
	}









































	public EmbedBuilder onJoin(User user, Guild guild){
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setColor(Color.green);
		embed.setFooter(String.valueOf(guild.getMembers().size()) + " Users!", null);
		embed.setTitle("User Joined!");
		embed.setThumbnail(user.getEffectiveAvatarUrl());
		embed.getDescriptionBuilder()
			.append("\n**User**\n")
			.append(user.getName() + "#" + user.getDiscriminator())
			.append("\n\n")
			.append("**User ID**\n")
			.append(user.getId());
		embed.setTimestamp(OffsetDateTime.now());
		
		return embed;
	}

}
