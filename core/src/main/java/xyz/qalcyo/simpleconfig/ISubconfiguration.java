package xyz.qalcyo.simpleconfig;

public interface ISubconfiguration<G> extends IConfiguration<G> {
    IConfiguration<G> getApex();
}