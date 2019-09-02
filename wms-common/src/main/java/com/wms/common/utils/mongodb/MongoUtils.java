package com.wms.common.utils.mongodb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.wms.common.core.domain.mongodb.BaseDomain;
import com.wms.common.utils.spring.SpringUtils;

public class MongoUtils {
	private static final Logger log = LoggerFactory.getLogger(MongoUtils.class);
	
	private static final String PRO_ID = "_id";
	
	/**
	 * @param objectId
	 * @param out
	 * @return 返回文件长度
	 */
	public static int getResource(ObjectId objectId , OutputStream out) {
		GridFSBucket bucket = SpringUtils.getBean(GridFSBucket.class);
        GridFSDownloadStream stream = null;
        int len = 0;
        try {
            stream = bucket.openDownloadStream(objectId);
            /** gridfs file */
            GridFSFile file = stream.getGridFSFile();
            /** chunk size */
            int size = file.getChunkSize();
            len = (int)file.getLength();
            /** loop time */
            int cnt = len / size + (len % size == 0 ? 0 : 1);
            byte[] bts = new byte[Math.min(len, size)];
            try {
                while (cnt-- > 0) {
                    int tmp = stream.read(bts);
                    out.write(bts, 0, tmp);
                }
                out.flush();
            } catch (IOException e) {
            	log.error(e.getMessage(), e);
            }
        } finally {
            if (stream != null) stream.close();
        }
        return len;
    }
	
	
	/**
	 * @param objectId
	 * @return 返回读取流
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static InputStream getResource(ObjectId objectId) throws IllegalStateException, IOException {
		GridFsTemplate gridFs = SpringUtils.getBean(GridFsTemplate.class);
		GridFSBucket bucket = SpringUtils.getBean(GridFSBucket.class);
		Query query = new Query(new GridFsCriteria(PRO_ID).is(objectId));
		GridFSFile file = gridFs.findOne(query);
        GridFsResource resource = new GridFsResource(file, bucket.openDownloadStream(objectId));
        return resource.getInputStream();
    }
	
	/**
	 * @param objectId
	 * @return 返回文件是否存在
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static Boolean resourceExists(ObjectId objectId) throws IllegalStateException, IOException {
		GridFsTemplate gridFs = SpringUtils.getBean(GridFsTemplate.class);
		Query query = new Query(new GridFsCriteria(PRO_ID).is(objectId));
		GridFSFile file = gridFs.findOne(query);
        return file != null;
    }
	
	public static void delete(ObjectId objectId) {
		GridFsTemplate gridFs = SpringUtils.getBean(GridFsTemplate.class);
		Query query = new Query(new GridFsCriteria(PRO_ID).is(objectId));
		gridFs.delete(query);
    }
	
	public static ObjectId store(InputStream content, String filename, String contentType) {
		GridFsTemplate gridFs = SpringUtils.getBean(GridFsTemplate.class);
		return gridFs.store(content, filename, contentType, (Object) null);
	}
	
	public static String save(BaseDomain saveObj) {
		MongoTemplate mt = SpringUtils.getBean(MongoTemplate.class);
		mt.save(saveObj);
		return saveObj.getId();
	}
	
	public static <T> List<T> find(Query query, Class<T> clz) {
		MongoTemplate mt = SpringUtils.getBean(MongoTemplate.class);
		return mt.find(query, clz);
    }
	
	public static <T> T findById(ObjectId id, Class<T> clz) {
		MongoTemplate mt = SpringUtils.getBean(MongoTemplate.class);
		return mt.findById(id, clz);
    }
	
	public static String insert(BaseDomain saveObj) {
		MongoTemplate mt = SpringUtils.getBean(MongoTemplate.class);
		mt.insert(saveObj);
		return saveObj.getId();
	}
}
