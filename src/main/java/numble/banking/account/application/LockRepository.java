package numble.banking.account.application;

public interface LockRepository {
    Boolean lock(String key);
    Boolean unLock(String key);
}
