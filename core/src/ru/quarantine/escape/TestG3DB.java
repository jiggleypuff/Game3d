package ru.quarantine.escape;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.UBJsonReader;

import java.util.Vector;

public class TestG3DB implements Screen {
    final MyGdxGame game;
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance modelInstance;
    private Environment environment;
    private AnimationController controller;

    public CameraInputController camController;

    AndroidGameControls androidGameControls;

    Player player;
    private Vector<Decal> decals;
    private Stage stage;
    private Skin skin;
    private DecalBatch decalBatch;


    public TestG3DB(final MyGdxGame myGdxGame) {
        game = myGdxGame;

        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f,3f,10f);
        camera.lookAt(0f,0f,0f);
        camera.near = 0.1f;
        camera.far = 300.0f;
        camera.update();

//        camController = new CameraInputController(camera);
//        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();

        // G3DB -----------------------------------------------------
        UBJsonReader jsonReader = new UBJsonReader();
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        model = modelLoader.loadModel(Gdx.files.getFileHandle("g3db/convertedModel.g3db", Files.FileType.Internal));
        modelInstance = new ModelInstance(model);
        // ----------------------------------------------------------

        modelInstance.transform.rotate(0, 10, 0, 0);
        modelInstance.transform.translate(0, 0, 0);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));

        var cameraController = new OrthographicCameraController(camera, modelInstance);
        // Handle inputs.
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        // TODO add any game-related actions you want to handle before the camera.
        inputMultiplexer.addProcessor(new GestureDetector(cameraController));
        inputMultiplexer.addProcessor(cameraController);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void update(){
        updateControls();

        if(!AndroidGameControls.isOnDesktop()){
            androidGameControls.updateControls(player);
        }
    }

    public void updateControls(){
        //Desktop controls - I guess they could be used with a BlueTooth keyboard on Android.
        if(Gdx.input.isKeyPressed(Input.Keys.W)) player.moveForward();
        if(Gdx.input.isKeyPressed(Input.Keys.S)) player.moveBackward();
        if(Gdx.input.isKeyPressed(Input.Keys.A)) player.moveLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) player.moveRight();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)) Gdx.app.exit();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))  player.turnLeft();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.turnRight();
    }

    @Override
    public void show() {
        decals = new Vector<Decal>();
//        enemyImgs = new Vector<Texture>();
//        enemyImgRegions = new Vector<TextureRegion>();

        float aspectRatio = (float) 800 / (float) 480;
        camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
        camera.near = 0.1f;

//        floorTex = new Texture("floortex.png");
//        floorTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        
//        enemyImgs.add(new Texture("enemy1.png"));
//        enemyImgs.add(new Texture("enemy2.png"));
        
//        for(Texture t : enemyImgs){
//            enemyImgRegions.add(new TextureRegion(t));
//        }

        VertexAttribute[] vaa = new VertexAttribute[3];
        vaa[0] =  new VertexAttribute(VertexAttributes.Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE );
        vaa[1] = new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ) ;
        vaa[2] = new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 4, ShaderProgram.COLOR_ATTRIBUTE );

        //set up floor (It's a single quad Mesh).
//        mesh = new Mesh(true, 4, 6, vaa);

//        mesh.setVertices(new float[] {
//                //x,y,z				//u, v		//r,g,b,a
//                -10f, -1f, -10,		0,10,      1,0,0,0.1f, //define counter clock wise vertices
//                10f,  -1f, -10,		10,10,	   1,0,0,0.7f,
//                10f,  -1f, 10,		10,0,	   1,0,0,0.5f,
//                -10f, -1f, 10, 		0,0,	   1,0,0,1
//        });

//        mesh.setIndices(new short[] { 0, 1, 2, 2, 3, 0});

//        mesh.getVertexAttribute(VertexAttributes.Usage.Position).alias = "a_position";

//        String vertexShader = Gdx.files.internal("vert.glsl").readString();
//        String  fragmentShader = Gdx.files.internal("frag.glsl").readString();
//        shaderProgram = new ShaderProgram(vertexShader,fragmentShader);

        decalBatch = new DecalBatch(new CameraGroupStrategy(camera));

//        decals.add(Decal.newDecal(1, 1, enemyImgRegions.get(0), true));
//        for(int i = 0; i < 4; i++){
//            decals.add(Decal.newDecal(1, 1, enemyImgRegions.get(1), true));
//        }

        player = new Player();

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();

        androidGameControls = new AndroidGameControls();
        androidGameControls.buildGameControls(stage, skin);
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Do all your basic OpenGL ES setup to start the screen render.
        Gdx.gl20.glClearColor(0.0f, 0.3f, 0.5f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl20.glCullFace(GL20.GL_NONE);
        Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);


        //set the camera to the players position and rotation.
        camera.position.set(player.getPos());
        camera.lookAt( player.getPos().x + (float) Math.sin(Math.toRadians(player.getYaw())), player.getPos().y,  player.getPos().z - (float) Math.cos(Math.toRadians(player.getYaw())));
        camera.update();

//        floorTex.bind();
//        shaderProgram.begin();
//        shaderProgram.setUniformMatrix("u_projTrans", camera.combined);
//        shaderProgram.setUniformi("u_texture", 0);
//        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
//        shaderProgram.end();

        //set decals up for visual testing.
//        decals.get(0).setPosition(0,-0.5f,0);
//        decals.get(1).setPosition(-1,-0.5f,-1);
//        decals.get(2).setPosition(-1,-0.5f,1);
//        decals.get(3).setPosition(1,-0.5f,1);
//        decals.get(4).setPosition(1,-0.5f,-1);

        for(Decal d : decals){
            d.lookAt(camera.position, camera.up);
            decalBatch.add(d);
        }

        //draw decals.
        decalBatch.flush();

        stage.getViewport().update(800, 480, true);

        stage.act(delta);
        stage.draw();

        modelBatch.begin(camera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {   }

    @Override
    public void pause() {   }

    @Override
    public void resume() {   }

    @Override
    public void hide() {   }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
