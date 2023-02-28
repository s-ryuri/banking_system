package numble.banking.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class MessageAlarmService implements AlarmService {

    @Override
    public void send() {
        try {
            Thread.sleep(3000);
            log.info("계좌이체가 완료되었습니다.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
