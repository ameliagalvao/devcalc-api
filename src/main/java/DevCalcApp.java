import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import java.util.Objects;

public class DevCalcApp {
    public static void main(String[] args) {
        CalculatorService calc = new CalculatorService();
        // Inicia Javalin com JSON padrão
        // Testando o novo workflow
        Javalin app = Javalin.create((JavalinConfig cfg) -> {
            cfg.http.defaultContentType = "application/json";
        }).start(7000);

        // Handler como Functional Interface
        Handler handle = (ctx, op) -> {
            try {
                double a = ctx.queryParamAsClass("a", Double.class)
                        .check(Objects::nonNull, "'a' é obrigatório")
                        .get();
                double b = ctx.queryParamAsClass("b", Double.class)
                        .check(Objects::nonNull, "'b' é obrigatório")
                        .get();
                ctx.json(op.apply(a, b));
            } catch (Exception e) {
                ctx.status(400).result(e.getMessage());
            }
        };

        // Rotas
        app.get("/calc/add",      ctx -> handle.handle(ctx, calc::add));
        app.get("/calc/subtract", ctx -> handle.handle(ctx, calc::subtract));
        app.get("/calc/multiply", ctx -> handle.handle(ctx, calc::multiply));
        app.get("/calc/divide",   ctx -> handle.handle(ctx, calc::divide));
    }

    @FunctionalInterface
    interface Operation {
        double apply(double a, double b);
    }

    @FunctionalInterface
    interface Handler {
        void handle(Context ctx, Operation op);
    }
}
