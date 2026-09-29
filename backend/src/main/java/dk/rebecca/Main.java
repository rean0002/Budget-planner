package dk.rebecca;

import dk.rebecca.dao.TransactionDAO;
import dk.rebecca.model.Transaction;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Database.initialize();

        TransactionDAO dao = new TransactionDAO();

        Javalin app = Javalin.create().start(7070);

        app.get("/", ctx -> ctx.result("Budget tracker backend kører!"));

        app.get("/transactions", ctx -> {
            ctx.json(dao.getAll());
        });

        app.post("/transactions", ctx -> {
            Transaction t = ctx.bodyAsClass(Transaction.class);
            dao.insert(t);
            ctx.status(201);
        });

        app.delete("/transactions/{id}", ctx->{
            String tekst =  ctx.pathParam("id");
            int tal = Integer.parseInt(tekst);
            dao.delete(tal);
            ctx.status(204);
        });

        app.put( "/transactions/{id}", ctx-> {
            Transaction t = ctx.bodyAsClass(Transaction.class);
            String tekst =  ctx.pathParam("id");
            dao.update(t, Integer.parseInt(tekst));
            ctx.status(204);
        });
    }
}