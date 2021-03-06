package com.eltechs.axs.gamesControls;

import com.eltechs.axs.GestureStateMachine.GestureContext;
import com.eltechs.axs.GestureStateMachine.GestureMouseMode;
import com.eltechs.axs.GestureStateMachine.GestureMouseMode.MouseModeState;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMeasureSpeed;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMoveToMouseDragAndDrop;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMoveToMouseMove;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMoveToScrollAsync;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerToZoomMove;
import com.eltechs.axs.GestureStateMachine.GestureState2FingersToZoom;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckIfZoomed;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckMouseMode;
import com.eltechs.axs.GestureStateMachine.GestureStateClickToFingerFirstCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateMouseWarpToFingerLastCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateNeutral;
import com.eltechs.axs.GestureStateMachine.GestureStatePressKey;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitFingersNumberChangeWithTimeout;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitForNeutral;
import com.eltechs.axs.GestureStateMachine.PointerContext;
import com.eltechs.axs.GuestAppActionAdapters.AlignedMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.AsyncScrollAdapterWithPointer;
import com.eltechs.axs.GuestAppActionAdapters.MouseClickAdapterWithCheckPlacementContext;
import com.eltechs.axs.GuestAppActionAdapters.OffsetMouseMoveAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndHoldMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndReleaseMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleDragAndDropAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMouseMoveAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMousePointAndClickAdapter;
import com.eltechs.axs.KeyCodesX;
import com.eltechs.axs.TouchArea;
import com.eltechs.axs.TouchEventMultiplexor;
import com.eltechs.axs.finiteStateMachine.FiniteStateMachine;
import com.eltechs.axs.finiteStateMachine.generalStates.FSMStateRunRunnable;
import com.eltechs.axs.geom.Rectangle;
import com.eltechs.axs.widgets.viewOfXServer.ViewOfXServer;

public class GestureMachineConfigurerCiv3 {
    private static final float clickAlignThresholdInches = 0.3f;
    private static final float doubleClickMaxDistance = 0.15f;
    private static final int doubleClickMaxIntervalMs = 200;
    private static final float fingerAimingMaxMoveInches = 0.2f;
    private static final int fingerSpeedCheckTimeMs = 200;
    private static final float fingerStandingMaxMoveInches = 0.12f;
    private static final float fingerTapMaxMoveInches = 0.2f;
    private static final int fingerTapMaxTimeMs = 100;
    private static final int maxTapTimeMs = 100;
    private static final int mouseActionSleepMs = 50;
    private static final int pointerMarginXPixels = 50;
    private static final float pointerOffsetAimInchesX = 0.0f;
    private static final float pointerOffsetAimInchesY = 0.0f;
    private static final float scrollThresholdPixels = 100.0f;

    public static GestureContext createGestureContext(ViewOfXServer viewOfXServer, TouchArea touchArea, TouchEventMultiplexor touchEventMultiplexor, int i, GestureMouseMode gestureMouseMode, Runnable runnable) {
        final GestureMouseMode gestureMouseMode2 = gestureMouseMode;
        ViewOfXServer viewOfXServer2 = viewOfXServer;
        final GestureContext gestureContext = new GestureContext(viewOfXServer2, touchArea, touchEventMultiplexor);
        PointerContext pointerContext = new PointerContext();
        GestureStateNeutral gestureStateNeutral = new GestureStateNeutral(gestureContext);
        GestureStateWaitForNeutral gestureStateWaitForNeutral = new GestureStateWaitForNeutral(gestureContext);
        float f = (float) i;
        float f2 = 0.2f * f;
        GestureState1FingerMeasureSpeed gestureState1FingerMeasureSpeed = new GestureState1FingerMeasureSpeed(gestureContext, 200, fingerStandingMaxMoveInches * f, f2, f2, scrollThresholdPixels);
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed = new GestureStateCheckIfZoomed(gestureContext);
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords = new GestureStateMouseWarpToFingerLastCoords(gestureContext, new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), pointerContext);
        GestureStateWaitForNeutral gestureStateWaitForNeutral2 = gestureStateWaitForNeutral;
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed2 = gestureStateCheckIfZoomed;
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), pointerContext);
        float f3 = clickAlignThresholdInches * f;
        ViewOfXServer viewOfXServer3 = viewOfXServer2;
        PointerContext pointerContext2 = pointerContext;
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords2 = gestureStateMouseWarpToFingerLastCoords;
        AlignedMouseClickAdapter alignedMouseClickAdapter = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), viewOfXServer3, pointerContext2, f3);
        float f4 = 0.15f * f;
        AlignedMouseClickAdapter alignedMouseClickAdapter2 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), viewOfXServer3, pointerContext2, f4);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter, alignedMouseClickAdapter, alignedMouseClickAdapter2, pointerContext, 200);
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords = new GestureStateClickToFingerFirstCoords(gestureContext, mouseClickAdapterWithCheckPlacementContext);
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter2 = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 3, 50), pointerContext);
        ViewOfXServer viewOfXServer4 = viewOfXServer2;
        PointerContext pointerContext3 = pointerContext;
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords2 = gestureStateClickToFingerFirstCoords;
        AlignedMouseClickAdapter alignedMouseClickAdapter3 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 3, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 3, 50), viewOfXServer4, pointerContext3, f3);
        AlignedMouseClickAdapter alignedMouseClickAdapter4 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 3, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 3, 50), viewOfXServer4, pointerContext3, f4);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext2 = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter2, alignedMouseClickAdapter3, alignedMouseClickAdapter4, pointerContext, 200);
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords3 = new GestureStateClickToFingerFirstCoords(gestureContext, mouseClickAdapterWithCheckPlacementContext2);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout2 = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        FSMStateRunRunnable fSMStateRunRunnable = new FSMStateRunRunnable(runnable);
        FSMStateRunRunnable fSMStateRunRunnable2 = new FSMStateRunRunnable(new Runnable() {
            public void run() {
                gestureMouseMode2.setState(MouseModeState.MOUSE_MODE_LEFT);
            }
        });
        GestureStatePressKey gestureStatePressKey = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_SPACE);
        GestureStatePressKey gestureStatePressKey2 = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_RETURN);
        float f5 = 0.0f * f;
        OffsetMouseMoveAdapter offsetMouseMoveAdapter = new OffsetMouseMoveAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), f5, f5);
        GestureStatePressKey gestureStatePressKey3 = gestureStatePressKey2;
        GestureStatePressKey gestureStatePressKey4 = gestureStatePressKey;
        GestureStatePressKey gestureStatePressKey5 = gestureStatePressKey3;
        SimpleDragAndDropAdapter simpleDragAndDropAdapter = new SimpleDragAndDropAdapter(offsetMouseMoveAdapter, new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 1), new Runnable() {
            public void run() {
                gestureContext.getPointerReporter().click(3, 50);
            }
        });
        OffsetMouseMoveAdapter offsetMouseMoveAdapter2 = offsetMouseMoveAdapter;
        GestureStatePressKey gestureStatePressKey6 = gestureStatePressKey4;
        FSMStateRunRunnable fSMStateRunRunnable3 = fSMStateRunRunnable2;
        FSMStateRunRunnable fSMStateRunRunnable4 = fSMStateRunRunnable;
        GestureState1FingerMoveToMouseDragAndDrop gestureState1FingerMoveToMouseDragAndDrop = new GestureState1FingerMoveToMouseDragAndDrop(gestureContext, simpleDragAndDropAdapter, pointerContext, true, 0.0f);
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove = new GestureState1FingerMoveToMouseMove(gestureContext, pointerContext, offsetMouseMoveAdapter2);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout3 = gestureStateWaitFingersNumberChangeWithTimeout;
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove2 = gestureState1FingerMoveToMouseMove;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout4 = gestureStateWaitFingersNumberChangeWithTimeout2;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout5 = gestureStateWaitFingersNumberChangeWithTimeout3;
        // GestureState1FingerMoveToScrollAsync gestureState1FingerMoveToScrollAsync = r1;
        GestureState1FingerMoveToScrollAsync gestureState1FingerMoveToScrollAsync /* 2 */ = new GestureState1FingerMoveToScrollAsync(gestureContext, new AsyncScrollAdapterWithPointer(gestureContext.getViewFacade(), new Rectangle(0, 0, gestureContext.getViewFacade().getScreenInfo().widthInPixels, gestureContext.getViewFacade().getScreenInfo().heightInPixels)), 1.0E7f, 1.0E7f, scrollThresholdPixels, true, 50, true);
        GestureState1FingerToZoomMove gestureState1FingerToZoomMove = new GestureState1FingerToZoomMove(gestureContext);
        GestureState2FingersToZoom gestureState2FingersToZoom = new GestureState2FingersToZoom(gestureContext);
        GestureStateCheckMouseMode gestureStateCheckMouseMode = new GestureStateCheckMouseMode(gestureContext, gestureMouseMode2);
        GestureStateCheckMouseMode gestureStateCheckMouseMode2 = new GestureStateCheckMouseMode(gestureContext, gestureMouseMode2);
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords3 = gestureStateMouseWarpToFingerLastCoords2;
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords4 = gestureStateClickToFingerFirstCoords2;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout6 = gestureStateWaitFingersNumberChangeWithTimeout5;
        GestureContext gestureContext2 = gestureContext;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout7 = gestureStateWaitFingersNumberChangeWithTimeout4;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout8 = gestureStateWaitFingersNumberChangeWithTimeout7;
        FSMStateRunRunnable fSMStateRunRunnable5 = fSMStateRunRunnable4;
        FSMStateRunRunnable fSMStateRunRunnable6 = fSMStateRunRunnable5;
        GestureStatePressKey gestureStatePressKey7 = gestureStatePressKey5;
        GestureStatePressKey gestureStatePressKey8 = gestureStatePressKey6;
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove3 = gestureState1FingerMoveToMouseMove2;
        GestureStatePressKey gestureStatePressKey9 = gestureStatePressKey7;
        GestureState1FingerMoveToScrollAsync gestureState1FingerMoveToScrollAsync3 = gestureState1FingerMoveToScrollAsync;
        GestureState2FingersToZoom gestureState2FingersToZoom2 = gestureState2FingersToZoom;
        FSMStateRunRunnable fSMStateRunRunnable7 = fSMStateRunRunnable3;
        finiteStateMachine.setStatesList(gestureStateNeutral, gestureState1FingerMeasureSpeed, gestureStateMouseWarpToFingerLastCoords3, gestureStateClickToFingerFirstCoords4, gestureStateClickToFingerFirstCoords3, gestureStateCheckIfZoomed2, gestureStateWaitFingersNumberChangeWithTimeout6, gestureStateWaitFingersNumberChangeWithTimeout7, fSMStateRunRunnable5, gestureStatePressKey6, gestureStatePressKey7, gestureState1FingerMoveToMouseDragAndDrop, gestureState1FingerMoveToMouseMove3, gestureState1FingerMoveToScrollAsync3, gestureState1FingerToZoomMove, gestureState2FingersToZoom, gestureStateCheckMouseMode, gestureStateCheckMouseMode2, fSMStateRunRunnable7, gestureStateWaitForNeutral2);
        GestureStateWaitForNeutral gestureStateWaitForNeutral3 = gestureStateWaitForNeutral2;
        finiteStateMachine.addTransition(gestureStateWaitForNeutral3, GestureStateWaitForNeutral.GESTURE_COMPLETED, gestureStateNeutral);
        finiteStateMachine.addTransition(gestureStateNeutral, GestureStateNeutral.FINGER_TOUCHED, gestureState1FingerMeasureSpeed);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_STANDING, gestureStateCheckMouseMode);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED, gestureStateCheckMouseMode);
        GestureStateWaitForNeutral gestureStateWaitForNeutral4 = gestureStateWaitForNeutral3;
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed3 = gestureStateCheckIfZoomed2;
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_FLASHED, gestureStateCheckIfZoomed3);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout6);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TAPPED, gestureStateCheckMouseMode2);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED_AND_GONE, gestureStateMouseWarpToFingerLastCoords3);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode, GestureStateCheckMouseMode.MOUSE_MODE_LEFT, gestureState1FingerMoveToMouseDragAndDrop);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode, GestureStateCheckMouseMode.MOUSE_MODE_RIGHT, gestureState1FingerMoveToMouseMove3);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode2, GestureStateCheckMouseMode.MOUSE_MODE_LEFT, gestureStateClickToFingerFirstCoords4);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode2, GestureStateCheckMouseMode.MOUSE_MODE_RIGHT, gestureStateClickToFingerFirstCoords3);
        finiteStateMachine.addTransition(gestureStateClickToFingerFirstCoords3, GestureStateClickToFingerFirstCoords.GESTURE_COMPLETED, fSMStateRunRunnable7);
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed3, GestureStateCheckIfZoomed.ZOOM_OFF, gestureState1FingerMoveToScrollAsync3);
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed3, GestureStateCheckIfZoomed.ZOOM_ON, gestureState1FingerToZoomMove);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout9 = gestureStateWaitFingersNumberChangeWithTimeout8;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout9);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey9);
        GestureState2FingersToZoom gestureState2FingersToZoom3 = gestureState2FingersToZoom2;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureState2FingersToZoom3);
        GestureStatePressKey gestureStatePressKey10 = gestureStatePressKey8;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout9, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey10);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout9, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureStatePressKey10);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout9, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, fSMStateRunRunnable6);
        finiteStateMachine.addTransition(gestureState2FingersToZoom3, GestureState2FingersToZoom.FINGER_RELEASED, gestureState1FingerToZoomMove);
        finiteStateMachine.addTransition(gestureState1FingerToZoomMove, GestureState1FingerToZoomMove.FINGER_TOUCHED, gestureState2FingersToZoom3);
        finiteStateMachine.setInitialState(gestureStateNeutral);
        finiteStateMachine.setDefaultState(gestureStateWaitForNeutral4);
        finiteStateMachine.configurationCompleted();
        GestureContext gestureContext3 = gestureContext2;
        gestureContext3.setMachine(finiteStateMachine);
        return gestureContext3;
    }
}
