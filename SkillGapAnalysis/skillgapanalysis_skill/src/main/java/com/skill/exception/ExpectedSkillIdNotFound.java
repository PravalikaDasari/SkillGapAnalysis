package com.skill.exception;

public class ExpectedSkillIdNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ExpectedSkillIdNotFound(String message, Exception exception) {
		super(message, exception);
	}

	public ExpectedSkillIdNotFound(String message) {
		super(message);
	}
}
