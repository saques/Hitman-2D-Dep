package com.mygdx.game;

import java.util.Set;

public interface MessageFilter<T extends Message> {
	Set<Listener> filter (T message, Set<Listener> baseListeners);
}
