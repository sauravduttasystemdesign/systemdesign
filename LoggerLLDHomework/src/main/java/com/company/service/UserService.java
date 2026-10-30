package com.company.service;

import com.company.logging.Logger;

public final class UserService {
    private final Logger logger;

    public UserService(Logger logger) {
        this.logger = logger;
    }

    public void login(String workflowId, String userId) {
        // Authentication logic would run here.
        logger.log("workflow=" + workflowId + " | UserService | User " + userId + " logged in");
    }
}
