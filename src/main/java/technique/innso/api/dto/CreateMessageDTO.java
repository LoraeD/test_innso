package technique.innso.api.dto;

import io.swagger.annotations.ApiModelProperty;
import technique.innso.enumeration.Channel;

public class CreateMessageDTO {

    @ApiModelProperty(value = "the message's author name", example = "Jérémie Durand", required = true)
    private String name;

    @ApiModelProperty(value = "the message's content", example = "Bonjour, j'ai un problème avec mon nouveau téléphone", required = true)
    private String content;

    @ApiModelProperty(value = "the channel where the message was post", example = "'MAIL'", allowableValues = "MAIL, SMS, FACEBOOK, TWITTER")
    private Channel channel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
