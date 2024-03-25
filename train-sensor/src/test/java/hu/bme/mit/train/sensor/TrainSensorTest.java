package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockC;
    TrainUser mockU;

    TrainSensor sensor;

    @Before
    public void before() {
        mockC = mock(TrainController.class);
        mockU = mock(TrainUser.class);

        sensor = new TrainSensor(mockC, mockU);
    }

    @Test
    public void testSpeedLimitUpper(){
        sensor.overrideSpeedLimit(600);

        verify(mockU, times(1)).setAlarmState(true);
    }

    @Test
    public void testSpeedInsidelimit(){
        sensor.overrideSpeedLimit(400);

        verify(mockU, times(1)).setAlarmState(false);
    }

    @Test
    public void testSpeedReferenceout(){
        when(mockC.getReferenceSpeed()).thenReturn(150);

        sensor.overrideSpeedLimit(50);
        verify(mockU, times(1)).setAlarmState(true);
    }

    @Test
    public void testSpeedReferencein(){
        when(mockC.getReferenceSpeed()).thenReturn(150);

        sensor.overrideSpeedLimit(100);
        verify(mockU, times(1)).setAlarmState(false);
    }
}
