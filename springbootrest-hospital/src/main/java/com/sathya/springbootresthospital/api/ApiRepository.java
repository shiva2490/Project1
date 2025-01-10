
package com.sathya.springbootresthospital.api;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRepository {

	private int statusCode;
	private String errorMessage;
	private LocalDateTime timestamp;
}
