package CSArmyBot.events;

import java.awt.Color;
import java.time.OffsetDateTime;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class GuildLeaveEvent implements EventListener{

	@Override
	public void onEvent(Event e) {
		if (e instanceof GuildMemberLeaveEvent){
			GuildMemberLeaveEvent event = (GuildMemberLeaveEvent) e;
			TextChannel channel = event.getGuild().getTextChannelsByName("leave-join-log", true).get(0);
			
			channel.sendMessage(onLeave((event).getUser(), event.getGuild()).build()).queue();
			//channel.sendMessage(event.getMember().getUser().getId()).queue();
		}
		
	}
	









































	public EmbedBuilder onLeave(User user, Guild guild){
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setColor(Color.red);
		embed.setFooter(String.valueOf(guild.getMembers().size()) + " Users!", null);
		embed.setTitle("User Left!");
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
