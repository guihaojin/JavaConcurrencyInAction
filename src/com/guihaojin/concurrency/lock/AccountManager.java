package com.guihaojin.concurrency.lock;

public class AccountManager {
    public static class NotEnoughMoneyException extends Exception {}

    /*
     * this implementation has dead lock issue!
     */
    public static void transferDeadLock(Account from, Account to, double money) throws NotEnoughMoneyException {
        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NotEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    public static boolean tryTransfer(Account from, Account to, double money) throws NotEnoughMoneyException {
        if (from.tryLock()) {
            try {
                if (to.tryLock()) {
                    try {
                        if (from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                            return true;
                        } else {
                            throw new NotEnoughMoneyException();
                        }
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    public static void tranfer(Account from, Account to, double money) throws NotEnoughMoneyException {
        boolean success;
        do {
            success = tryTransfer(from, to, money);
            if (!success) {
                Thread.yield();
            }
        } while (!success);
    }
}
