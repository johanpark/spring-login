package hello.login.web.session;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@Log4j2
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "세션이 없습니다.";
        }

        //보관한 세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}", name, session.getAttribute(name)));

        log.info("session = {}", session.getId());
        log.info("getMaxInactiveInterval = {}", session.getMaxInactiveInterval());
        log.info("creationTime = {}", new Date(session.getCreationTime()));
        log.info("getLastAccessedTime = {}", new Date(session.getLastAccessedTime()));
        log.info("isNew = {}", session.isNew());

        return "세션 출력";
    }
}
