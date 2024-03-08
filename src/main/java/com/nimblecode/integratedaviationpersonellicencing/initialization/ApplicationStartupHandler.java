package com.nimblecode.integratedaviationpersonellicencing.initialization;

import com.nimblecode.integratedaviationpersonellicencing.service.RolesService;
import com.nimblecode.integratedaviationpersonellicencing.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartupHandler implements ApplicationListener<ApplicationReadyEvent> {
    private final RolesService rolesService;
    private final UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("INITIALIZING...");
        rolesService.initialize();
        userService.initialize();
        log.info("INITIALIZATION COMPLETE");
    }
}
