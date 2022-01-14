package n.nikitins.maze.simple_generator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import n.nikitins.maze.simple_generator.models.quickGame.QuickGame;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGameViewModel;

//import android.util.Log;

public class GameView extends View {

    Context context;
    private QuickGameViewModel quickGameViewModel;
    int rows;
    int columns;
    int cellSize;
    int level;
    int height;
    int width;
    int moveCount;
    int maxMoves;
    Paint paint;
    MazeGenerator mazeGenerator;
    String timeTaken;
    TextView moves;
    int gameMode;
    int currentX;
    int currentY;

    /*
     * store x and y coordinates of top left corner
     * and if the maze has been touched or not
     */
    int mazeX[][];
    int mazeY[][];
    boolean mazeColor[][];

    /*
     * Variables related to zooming and panning on the canvas
     * link : http://vivin.net/2011/12/04/implementing-pinch-zoom-and-pandrag-in-an-android-view-on-the-canvas/
     */
    private static float MIN_ZOOM = 1f;
    private static float MAX_ZOOM = 5f;
    private float scaleFactor = 1.f;
    private ScaleGestureDetector detector;

    private static int NONE = 0;
    private static int DRAG = 1;
    private static int ZOOM = 2;
    private int mode;

    private float startX = 0f;
    private float startY = 0f;

    private float startXMovement = 0f;
    private float startYMovement = 0f;

    private float translateX = 0f;
    private float translateY = 0f;

    private float previousTranslateX = 0f;
    private float previousTranslateY = 0f;

    private boolean dragged = true;

    private boolean screenTouched = false;

    boolean GameOver;

    public GameView(Context context, int height, int width, TextView moves,
                    int level, int mode, QuickGameViewModel quickGameViewModel) {
        super(context);

        this.context = context;
        this.height = height;
        this.width = width;
        this.moves = moves;
        this.level = level;
        this.gameMode = mode;
        this.quickGameViewModel = quickGameViewModel;

        Log.d("game view mode", "" + gameMode);
        GameOver = false;

        paint = new Paint();

        if (gameMode == -1)
            setDimensions(0);

    }

    public void setDimensions(int TextViewHeight) {

        TextViewHeight += 20;
        height -= 2 * TextViewHeight;
        //	Log.d("Height, Width", height + "," + width);
        intializeValues();
    }

    public void intializeValues() {

        if (gameMode <= 0) { //practice and quickgame

            if (level == 1) {
                rows = 10;
                columns = 5;
            } else if (level == 2) {
                rows = 20;
                columns = 10;
            } else if (level == 3) {
                rows = 40;
                columns = 20;
            } else if (level == 4) {
                rows = 80;
                columns = 40;
            } else if (level == 5) {
                rows = 100;
                columns = 50;
            }

            this.cellSize = Math.min(height / rows, width / columns);

            mazeGenerator = new MazeGenerator(rows, columns);
            mazeGenerator.createMazeKruskals();
            mazeGenerator.getDestinationPoints();
            Log.d("maze created", "complete");

        }

        mazeX = new int[rows][columns];
        mazeY = new int[rows][columns];
        mazeColor = new boolean[rows][columns];

        createMazeCoordinates();


        moveCount = (int) Math.min(Math.ceil(mazeGenerator.max_distance + (0.25 * mazeGenerator.max_distance)), (rows * columns) - 1);
        maxMoves = moveCount;
        if (gameMode != -1)
            moves.setText("Moves Left : " + moveCount);
    }

    //assign coordinates of top left corner of each cell
    public void createMazeCoordinates() {

        int start_x = (width - columns * cellSize) / 2;
        int x = start_x; //starting x coord top left
        int y = 0; //starting y coord top left

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                mazeX[i][j] = x;
                mazeY[i][j] = y;
                mazeColor[i][j] = false;
                x += cellSize;
                //Log.d(i+","+j,x+","+y);
            }
            y += cellSize;
            x = start_x;
        }

        mazeColor[0][0] = true;
        currentX = 0;
        currentY = 0;
    }


    //Where all the drawing happens
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);//,this.detector.getFocusX(),this.detector.getFocusY());

        if ((translateX * -1) < 0) { //left bound
            translateX = 0;
        } else if ((translateX * -1) > (scaleFactor - 1) * width) { //right bound
            translateX = (1 - scaleFactor) * width;
        }

        if (translateY * -1 < 0) {//top bound
            translateY = 0;
        } else if ((translateY * -1) > (scaleFactor - 1) * height) { //bottom bound
            translateY = (1 - scaleFactor) * height;
        }


        canvas.translate(translateX / scaleFactor, translateY / scaleFactor);

        paint.setColor(Color.BLACK);

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                //draw hero
                if (mazeColor[i][j]) {
                    Drawable d = getResources().getDrawable(R.drawable.rapper, null);
                    d.setBounds(mazeX[i][j], mazeY[i][j], mazeX[i][j] + cellSize, mazeY[i][j] + cellSize);
                    d.draw(canvas);
                }

                //if cell is destination color it red
                if (i == mazeGenerator.dest_row && j == mazeGenerator.dest_col) {
                    Drawable d = getResources().getDrawable(R.drawable.santaclaus, null);
                    d.setBounds(mazeX[i][j], mazeY[i][j], mazeX[i][j] + cellSize, mazeY[i][j] + cellSize);
                    d.draw(canvas);
                }

                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(3);

                if (mazeGenerator.walls[i][j][0])
                    canvas.drawLine(mazeX[i][j], mazeY[i][j], mazeX[i][j] + cellSize, mazeY[i][j], paint);
                if (mazeGenerator.walls[i][j][1])
                    canvas.drawLine(mazeX[i][j] + cellSize, mazeY[i][j], mazeX[i][j] + cellSize, mazeY[i][j] + cellSize, paint);
                if (mazeGenerator.walls[i][j][2])
                    canvas.drawLine(mazeX[i][j], mazeY[i][j] + cellSize, mazeX[i][j] + cellSize, mazeY[i][j] + cellSize, paint);
                if (mazeGenerator.walls[i][j][3])
                    canvas.drawLine(mazeX[i][j], mazeY[i][j], mazeX[i][j], mazeY[i][j] + cellSize, paint);

            }
        }
        canvas.restore();
    }


    public void moveCell(int direction) {
        //Log.d("direction",direction+"");
        if (direction == 3 && currentY - 1 >= 0 && !mazeGenerator.walls[currentX][currentY][3]) {
            //Log.d("3","true");
            mazeColor[currentX][currentY] = false;
            currentY = currentY - 1;
            mazeColor[currentX][currentY] = true;
            if (gameMode != -1)
                moveCount--;
        } else if (direction == 1 && currentY + 1 < columns && !mazeGenerator.walls[currentX][currentY][1]) {
            //	Log.d("1","true");
            mazeColor[currentX][currentY] = false;
            currentY = currentY + 1;
            mazeColor[currentX][currentY] = true;
            if (gameMode != -1)
                moveCount--;
        } else if (direction == 0 && currentX - 1 >= 0 && !mazeGenerator.walls[currentX][currentY][0]) {
            //Log.d("0","true");
            mazeColor[currentX][currentY] = false;
            currentX = currentX - 1;
            mazeColor[currentX][currentY] = true;
            if (gameMode != -1)
                moveCount--;
        } else if (direction == 2 && currentX + 1 < rows && !mazeGenerator.walls[currentX][currentY][2]) {
            //Log.d("2","true");
            mazeColor[currentX][currentY] = false;
            currentX = currentX + 1;
            mazeColor[currentX][currentY] = true;
            if (gameMode != -1)
                moveCount--;
        }
        if (gameMode != -1)
            moves.setText("Moves Left : " + moveCount);
        if (currentX == mazeGenerator.dest_row && currentY == mazeGenerator.dest_col)
            gameOver(0);
        else if (moveCount == 0 && gameMode != -1)
            gameOver(1);

        invalidate();
    }

    //user has reached the final cell
    //game over
    public void gameOver(int flag) {

        AlertDialog.Builder builder = new Builder(context);

        ((GameActivity) getContext()).stopTimer = true;
        GameOver = true;

        if (flag == 0) {
            if (gameMode == -1)
                builder.setTitle("SUCCESS").setMessage("Well Done!!");
            else
                builder.setTitle("SUCCESS").setMessage("Well Done!!\nTime Taken : " + timeTaken);
            builder.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (gameMode == 0) { //Quick Game
                        // DB.updateQuickGameStats(level, 1, timeTaken);
                        updateQuickGameStats(level, 1, timeTaken);
                        Activity activity = (Activity) getContext();
                        activity.finish();

                    } else if (gameMode == -1) //Practice
                    {
                        Activity activity = (Activity) getContext();
                        activity.finish();
                    }

                }
            });
        } else if (flag == 1) {
            builder.setTitle("FAIL").setMessage("You Lose!\nTime Taken : " + timeTaken);
            builder.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (gameMode == 0) {
                        updateQuickGameStats(level, 0, timeTaken);

                        Activity activity = (Activity) getContext();
                        activity.finish();
                    }

                }
            });

        }
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public boolean onTouchEvent( MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startXMovement = x;
                startYMovement = y;
                return true;
            }
            case MotionEvent.ACTION_UP: {
                if (Math.abs((startYMovement - y)) > Math.abs((startXMovement - x) )) {
                    if (y < startYMovement) {
                        moveCell(0);
                    } else {
                        moveCell(2);
                    }
                } else {
                    if (x > startXMovement) {
                        moveCell(1);
                    } else {
                        moveCell(3);
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                return true;
            }
        }
        return false;
    }

    private void updateQuickGameStats(int level, int result , String timeTaken) {
        QuickGame previousResult = quickGameViewModel.getQuickGameByLevel(level);
        Log.i("res:prev:time", previousResult.fastestWin);
        Log.i("res:next:time", timeTaken);

        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            Date prevTimeResult = dateFormat.parse(previousResult.fastestWin);
            Date newTime = dateFormat.parse(timeTaken);

            if ((prevTimeResult.getTime() - newTime.getTime()) < 0 && result == 1){
                previousResult.fastestWin = timeTaken;
            }

        } catch (ParseException e) {
        }

        if (result == 1) {
            previousResult.totalWins += 1;
        } else {
            previousResult.totalLoses += 1;
        }
        previousResult.gamesPlayed++;

        quickGameViewModel.update(previousResult);
    }
}