package uz.doniyor.springbootmongodb.exception;

public class TodoCollectionExeption extends Exception {

    private static final long serialVersionUID = 1L;


    public TodoCollectionExeption(String messsage) {
        super(messsage);
    }

    public static String NotFoundException(String id) {
        return "Todo with" + id + " not found!";
    }

    public static String TodoAlreadyExists(){
        return "Todo with given name already exists";
    }
}
