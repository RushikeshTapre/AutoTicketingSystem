package exception;

public class ParkingSlotHandlingException extends Exception{

    Integer errorCode;

    public ParkingSlotHandlingException(String errorMessage){
        super(errorMessage);
    }


}
