package com.codewithwaruni.asset.payloads;

import java.util.Date;

import com.codewithwaruni.asset.entities.Category;
import com.codewithwaruni.asset.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer  postId;
	private  String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;

}
