package com.rafael.pedido.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum State {
	Pendiente("Pendiente"),
	Aprovado("Aprobado"),
	Candelado("Cancelado");
	
	private String state;
	
	public static String getStateDesc(State estado) {
		for(State st: State.values()) {
			if (st.getState().equals(estado.getState())) {
				return st.getState();
			}
		}
		return null;
	}
	
	public static State getStateEnun(String estado) {
		for(State st: State.values()) {
			if (st.getState().equals(estado)) {
				return st;
			}
		}
		return null;
	}
}
