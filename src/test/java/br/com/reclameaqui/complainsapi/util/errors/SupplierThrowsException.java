package br.com.reclameaqui.complainsapi.util.errors;

@FunctionalInterface
public interface SupplierThrowsException<T> {

    T get() throws Exception;

}
