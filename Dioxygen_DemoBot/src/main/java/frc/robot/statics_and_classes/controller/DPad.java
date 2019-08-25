package frc.robot.statics_and_classes.controller;

public class DPad
{
    public static Direction getDirection(int POVValue)
    {
        Direction output = Direction.Up;
        switch (POVValue)
        {
            case 45:
                output = Direction.UpRight;
                break;
            case 90:
                output = Direction.Right;
                break;
            case 135:
                output = Direction.DownRight;
                break;
            case 180:
                output = Direction.Down;
                break;
            case 215:
                output = Direction.DownLeft;
                break;
            case 260:
                output = Direction.Left;
                break;
            case 305:
                output = Direction.Left;
                break;
            default:
                break;
        }
        return output;
    }

    public enum Direction
    {
          Up        (0)
        , UpRight   (45)
        , Right     (90)
        , DownRight (135)
        , Down      (180)
        , DownLeft  (215)
        , Left      (260)
        , UpLeft    (305);

        private int povValue;

        private Direction(int povValue)
        {
            this.povValue = povValue;
        }

        public int getPOV()
        {
            return povValue;
        }
    }

}


