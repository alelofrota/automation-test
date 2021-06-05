
package br.com.alelo.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "API error response")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
public class ErroInfo implements Serializable {

     /**
      * 
      */
     private static final long serialVersionUID = 1L;

     @ApiModelProperty(value = "Error date and time", example = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
     @JsonSerialize(using = LocalDateTimeSerializer.class)
     @JsonDeserialize(using = LocalDateTimeDeserializer.class)
     public LocalDateTime timestamp;

     @ApiModelProperty(value = "Error code", example = "404")
     public Integer code;

     @ApiModelProperty(value = "Exception thrown", example = "\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\"")
     public String exception;

     @ApiModelProperty(value = "List of error messages", example = "[ { \"Invalid request\" } ] ")
     public List<String> messages;

     @ApiModelProperty(value = "Path of the call that caused the error", example = "\\path")
     public String path;

}
