import react,{Component} from 'react';
import display from '../assets/pet.png';
import {List,Avatar} from 'antd';
import styles from './index.css';
import Link from 'umi/link';
import {connect} from 'dva';


const mapStateToProps = (state) =>{
  return {
    list: state.list
  }
}

const mapDispatchToProps = (dispatch) =>{
  return {
    fetch: ()=>dispatch({type:"list/fetchData"})
  }
}

@connect(mapStateToProps,mapDispatchToProps)
class Service extends Component{
  componentDidMount(){
    this.props.fetch();
  }
  render(){
    const data = [
      {
        title: '添加Service',
      },
      {
        title: '删除Service',
      },
      {
        title: '编辑Service信息',
      },
    ];
    
    return (
    <div className={styles.list}>
      <List
        itemLayout="horizontal"
        dataSource={data}
        renderItem={item => (
          <List.Item actions={[<Link to="/">Back to index</Link>]}>
        <List.Item.Meta
          //avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
          title={<a href="https://ant.design">{item.title}</a>}
          //description="Ant Design, a design language for background applications, is refined by Ant UED Team"
        />
      </List.Item>
        )}
      />
    </div>
    )
    
  }
}

export default Service;