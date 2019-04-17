package frc.robot.statics_and_classes.controller_profiles;

public class MadCatzV1 {
    /*
     * Represents a digital button on an MadCatzV1 controller.
     */
    private enum Button{
        XAxis(1)
        ,YAxis(2)
        ,ZAxis(3)
        ,AAxis(4);

        private final int value;
    
        Button(int value) {
          this.value = value;
        }
    }

    // copy the XboxController methods but adapted for the MadCatz Controller

}